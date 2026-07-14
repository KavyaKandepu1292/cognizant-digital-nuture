ngOnInit(): void {
  this.employeeService.getAllEmployees().subscribe(
    (data) => {
      this.employees = data;
    }
  );
}