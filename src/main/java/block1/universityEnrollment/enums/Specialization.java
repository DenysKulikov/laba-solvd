package block1.universityEnrollment.enums;

public enum Specialization {
    SOFTWARE_ENGINEERING(190),
    APPLIED_MATH(180);
    int averageExamScore;

    Specialization(int averageExamScore) {
        this.averageExamScore = averageExamScore;
    }

    public int getAverageExamScore() {
        return averageExamScore;
    }
}
