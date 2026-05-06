import { Component } from '@angular/core';
import { BoardCase } from '../../../types/BoardCase';
import { DrawerModule } from 'primeng/drawer';
import { ClientRecord } from '../../../types/ClientRecord';
import { Clientrecordservice } from '../../../services/clientrecordservice';
import { AuthRoleService } from '../../../services/authroleservice';
import { CaseColumnModel } from '../../../types/CaseColumnModel';
import { CaseColumnComponent } from '../../../components/case-column/case-column';
import { OnboardingCaseService } from '../../../services/OnboardingCaseService';
import { DetailModal } from '../../../components/detail-modal/detail-modal';
import { ButtonModule } from 'primeng/button';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { DatePickerModule } from 'primeng/datepicker';
import { InitializeCaseModal } from '../../../components/initialize-case-modal/initialize-case-modal';


@Component({
  selector: 'app-cases-dashbaord',
  imports: [DrawerModule, CaseColumnComponent, DetailModal, ButtonModule,
    FormsModule, DatePickerModule, InitializeCaseModal
  ],
  templateUrl: './cases-dashboard.html',
  styleUrl: './cases-dashboard.css',
})
export class CasesDashbaord {
  clientMap: Map<string, ClientRecord> = new Map();
  get clientsArray(): ClientRecord[] {
    return Array.from(this.clientMap.values());
  }
  showDetailDialog = false;
  showInitializeDialog = false;
  selectedClient: ClientRecord | undefined = undefined;

  allCases: BoardCase[] = [];
  columns: CaseColumnModel[] = [
    {
      id: 'initiated',
      name: 'Initiated',
      stage: 'INITIATED',
      cases: []
    },
    {
      id: 'review',
      name: 'Under Review',
      stage: 'UNDER_REVIEW',
      cases: []
    },
    {
      id: 'kyc',
      name: 'KYC In Progress',
      stage: 'KYC_IN_PROGRESS',
      cases: []
    },
    {
      id: 'approved',
      name: 'Approved',
      stage: 'APPROVED',
      cases: []
    },
    {
      id: 'rejected',
      name: 'Rejected',
      stage: 'REJECTED',
      cases: []
    }
  ];
  
  constructor(
    private clientservice: Clientrecordservice, 
    private caseservice: OnboardingCaseService,
    private auth: AuthRoleService,
    private router: Router
  ) {this.role = this.auth.role()}
  role: string | undefined;
  
  ngOnInit(): void {
   this.loadClients();
  }

  loadClients()
  {
     this.clientservice.getClientRecordOnboard().subscribe({
      next: (records) => {
        for (const record of records) {
          this.clientMap.set(record.id, record)
          if (record.boardcase) {
            const column = this.columns.find(
              col => col.stage === record.boardcase!.stage // we checked if record.boardcase was truthy above so we can say it's not undefined here
            );

            if (record.boardcase) {
              this.allCases.push(record.boardcase);
              console.log(record.boardcase);
              if (column)
                column.cases.push(record.boardcase);
            }
          }
        }
      },
      error: (err) => {
        console.error("Can't retrieve clients", err);
      }
    });
  }

  canMove(caseItem: BoardCase, direction: 'left' | 'right') {
    const rules = {
      INITIATED: { left: false, right: true },
      UNDER_REVIEW: { left: true, right: true },
      KYC_IN_PROGRESS: { left: true, right: true },
      APPROVED: { left: true, right: false },
      REJECTED: { left: true, right: false }
    };

    return rules[caseItem.stage]?.[direction] ?? false;
  }

  onMoveRight(caseItem: BoardCase) {
    this.moveCase(caseItem, 'RIGHT');
  }
  onMoveLeft(caseItem: BoardCase) {
    this.moveCase(caseItem, 'LEFT');
  }
  moveCase(caseItem: BoardCase, direction: 'LEFT' | 'RIGHT') {
    const currentIndex = this.columns.findIndex(
      col => col.stage === caseItem.stage
    );

    if (currentIndex === -1) return;

    const newIndex =
      direction === 'LEFT' ? currentIndex - 1 : currentIndex + 1;

    // boundary check
    if (newIndex < 0 || newIndex >= this.columns.length) return;

    const currentColumn = this.columns[currentIndex];
    const targetColumn = this.columns[newIndex];

    // remove from current
    currentColumn.cases = currentColumn.cases.filter(
      c => c.id !== caseItem.id
    );

    // update state
    caseItem.stage = targetColumn.stage;

    // add to new
    targetColumn.cases.push(caseItem);

    // finally, update stage in DB
    this.caseservice.updateCase(caseItem).subscribe({
      next: () => {},
      error: (err) => {
        console.error('Error updating', err);
      }
    });
  }


  openCase(caseItem: BoardCase) {
    this.selectedClient = this.clientMap.get(caseItem.clientId);
    if (this.selectedClient) this.selectedClient.boardcase = caseItem;
    this.showDetailDialog = true;
  }
  openInitializeCaseModal() {
    this.showInitializeDialog=true;
  } 

  handleCreateCase(caseItem: BoardCase) {
    this.caseservice.addCase(caseItem).subscribe({
      next: () => {},
      error: (err) => {
        console.error('Error updating', err);
      }
    });
  }

  searchTerm = '';
  showDatePicker = false;
  dateRange: Date[] | null = null;
  onSearchChange() {}
  get filteredColumns(): CaseColumnModel[] {

    const term = this.searchTerm.toLowerCase().trim();

    const filteredCases = this.allCases.filter(c => {
      const client = this.clientMap.get(c.clientId);

      // TEXT FILTER
      const matchesText =
        !term ||
        client?.firstName?.toLowerCase().includes(term) ||
        client?.lastName?.toLowerCase().includes(term) ||
        client?.email?.toLowerCase().includes(term) ||
        c.id?.toLowerCase().includes(term);

      // DATE FILTER
      let matchesDate = true;

      if (this.dateRange && this.dateRange.length === 2) {
        const [start, end] = this.dateRange;

        const caseDate = new Date(c.dateInitiated);

        matchesDate =
          caseDate >= start &&
          caseDate <= end;
      }

      return matchesText && matchesDate;
    });

    return this.columns.map(col => ({
      ...col,
      cases: filteredCases.filter(c => c.stage === col.stage)
    }));
  }
}
