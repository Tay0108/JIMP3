public class PracownikEtatowy extends Pracownik{
    double podatek;

    public PracownikEtatowy(int pesel) {
        this.podatek = 0.3;
        this.pesel = pesel;                                 //pole klasy nadrzednej
    }

    @Override
    public double wynagrodzenieNetto() {
        return wynagrodzenieBrutto*(1-podatek);
    }
}
