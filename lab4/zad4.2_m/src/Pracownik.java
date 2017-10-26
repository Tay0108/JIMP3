public abstract class Pracownik {
    int pesel;
    double wynagrodzenieBrutto;

    public abstract double wynagrodzenieNetto();

    /*
    TODO: add validation of input pesel, you can use the module zad1.6
     */

    public int getPesel() {
        return this.pesel;
    }

    public void setWynagrodzenieBrutto(double wynagrodzenieBrutto) {
        this.wynagrodzenieBrutto = wynagrodzenieBrutto;
    }
}
