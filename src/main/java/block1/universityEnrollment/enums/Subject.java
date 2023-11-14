package block1.universityEnrollment.enums;

public enum Subject {
    MATH(4000),
    PHYSIC(3900),
    PROGRAMMING(4000),
    UKRAINIAN_LANGUAGE(3800);
    final double subjectCost;

    Subject(double subjectCost) {
        this.subjectCost = subjectCost;
    }

    public double getCost() {
        return subjectCost;
    }
}
