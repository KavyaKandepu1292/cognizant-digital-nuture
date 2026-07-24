class Cohort {
    constructor(cohortCode,
        startDate,
        technology,
        trainerName,
        coachName,
        currentStatus) {
        this.cohortCode = cohortCode;
        this.coachName = coachName;
        this.trainerName = trainerName;
        this.technology = technology;
        this.startDate = startDate;
        this.currentStatus = currentStatus;
    }
}
const CohortsData =[
    new Cohort('BTC-2601','15-Jan-2026', 'React FSD', 'Meera Nair','Aditya', 'Ongoing'),
    new Cohort('BTC-2602','02-Mar-2026', 'Spring Boot FSD', 'Karan Shah','Divya', 'Scheduled'),
    new Cohort('BTC-2603','18-Nov-2025', 'Python FSD', 'Neha Gupta','Aditya', 'Completed'),
    new Cohort('BTC-2604','15-Jan-2026', 'DevOps', 'Vikram Rao','Sunita', 'Ongoing'),
    new Cohort('BTC-2605','18-Nov-2025', 'Java FSD', 'Farah Khan','Divya', 'Completed'),
    new Cohort('BTC-2606','02-Mar-2026', 'Data Engineering', 'Rohit Iyer','Sunita', 'Scheduled'),
    new Cohort('BTC-2607','15-Jan-2026', '.NET FSD', 'Anjali Menon','Aditya', 'Ongoing'),
];
export {Cohort, CohortsData};