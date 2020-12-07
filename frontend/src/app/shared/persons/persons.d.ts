export interface Person {
  personalId: string;
  firstName: string;
  lastName: string;
  gender: string;
  dateOfBirth: string;
}

export interface PersonsResponse {
  content: Person[];
  total: number;
}

export interface PersonsRequestParams {
  firstName: string;
  lastName: string;
  gender: string;
  fromDateOfBirth: string;
  toDateOfBirth: string;
  pageIndex: number;
}
