export interface Check {
  date: string; 
  notes: string;
  result: 'PASS' | 'FAIL' | 'PENDING_REVIEW';
  type: 'ID Verification' | 'Sanctions Screening' | 'PEP Screening' | 'Adverse Media Check';
}
