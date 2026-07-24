class Employee {
    constructor(id, name, email, phone){
        this.id=id;
        this.name=name;
        this.email=email;
        this.phone=phone;
    }
}

const EmployeesData=[
    new Employee(101,'Meera','meera@cognizant.com','98230011122'),
    new Employee(102,'Karthik','karthik@cognizant.com', '9981223344'),
    new Employee(103,'Divya','divya@cognizant.com','9989556677')
];

export default Employee;
export {EmployeesData};