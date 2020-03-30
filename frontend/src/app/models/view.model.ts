export interface Pageable {
  offset: number;
  pageSize: number;
  pageNumber: number;
  unpaged: boolean;
  paged: boolean;
  sort: Sort;
}

export interface Sort {
  sorted: boolean;
  unsorted: boolean;
  empty: boolean;
}
