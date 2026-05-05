export interface Check {
  date: string; 
  notes: string;
  result: 'Pass' | 'Fail' | 'Pending Review';
  type: 'ID Verification' | 'Sanctions Screening' | 'PEP Screening' | 'Adverse Media Check';
}
