public class Student extends Pracownik {
    final double podatek;

    public Student(int pesel) {
        this.podatek = 0.19;
        this.pesel = pesel;                                 //pole klasy nadrzednej
    }

    @Override
    public double wynagrodzenieNetto() {
        return wynagrodzenieBrutto*(1-podatek);
    }
}
