/*
TODO: lepiej to gówno napisać od nowa :)
 */


public class PracownikProgram {
    public static void main(String[] args) {

        Student jasiu = new Student(96308);
        jasiu.setWynagrodzenieBrutto(1000);
        System.out.println("pesel: " + jasiu.getPesel() + ", pensja: " + jasiu.wynagrodzenieNetto());

        PracownikEtatowy panJanek = new PracownikEtatowy(96062);
        panJanek.setWynagrodzenieBrutto(1000);
        System.out.println("pesel: " + jasiu.getPesel() + ", pensja: " + panJanek.wynagrodzenieNetto());

        Kadry kadra = new Kadry();
        kadra.dodaj(jasiu);
        kadra.dodaj(jasiu);
        kadra.dodaj(jasiu);
    }
}
