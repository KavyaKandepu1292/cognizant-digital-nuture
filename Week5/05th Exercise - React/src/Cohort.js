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
    new Cohort('INTADMDF21','15-Jan-2026', '.NET FSD', 'Meera Nair','Sunita', 'Scheduled'),
    new Cohort('ADM26JF002','20-Nov-2025', 'Java FSD', 'Karan Shah','Apoorv', 'Ongoing'),
    new Cohort('CDBJF26014','05-Mar-2026', 'Java FSD', 'Divya Rao','Aathma', 'Ongoing'),
    new Cohort('INTADMJF07','15-Jan-2026', 'Java FSD', 'To Be Assigned','Ibrahim', 'Scheduled'),
    new Cohort('CDE26JF009','05-Mar-2026', 'Java FSD', 'Vikram Menon','Apoorv', 'Ongoing'),
    new Cohort('INTADMDF18','15-Jan-2026', 'Data Engineering', 'Neha Gupta','Aathma', 'Scheduled'),
    new Cohort('ADM26DF003','20-Nov-2025', '.NET FSD', 'Arjun Dev','Ibrahim', 'Ongoing'),
];
export {Cohort, CohortsData};