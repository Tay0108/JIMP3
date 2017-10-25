/**
 * the source of knowledge about builder pattern:
 * https://jlordiales.wordpress.com/2012/12/13/the-builder-pattern-in-practice/
 */

/*
    TODO: add send method
 */

import java.util.LinkedList;

public class EmailMessage {

    private String from;                //required (must be e-mail)
    private LinkedList<String> to;      //required at least one (must be e-mail)
    private String subject;             //optional
    private String content;             //optional
    private String mimeType;            // optional
    private LinkedList<String> cc;      //optional
    private LinkedList<String> bcc;     // optional

    /* konstruktor jest private wiec nie mozna utworzyc obiektu bezposrednio z niego
     * do tego celu trzeba uzyc buildera */

    private EmailMessage(Builder builder) {
        this.from = builder.from;
        this.to = builder.to;
        this.subject = builder.subject;
        this.content = builder.content;
        this.mimeType = builder.mimeType;
        this.cc = builder.cc;
        this.bcc = builder.bcc;
    }

    /* getters bo pola jest private (tak dla picu) */

    public String getFrom() { return from; }
    public LinkedList<String> getTo() { return to; }
    public String getSubject() { return subject; }
    public String getContent() { return content; }
    public String getMimeType() { return mimeType; }
    public LinkedList<String> getCc() { return cc; }
    public LinkedList<String> getBcc() { return bcc; }

    /* builder - instancji tej klasy uzywa konstruktor klasy EmailMessage */

    public static class Builder {
        private final String from;              //required -> immutable
        private final LinkedList<String> to;    //required -> immutable
        private String subject;                 //all the below optional
        private String content;
        private String mimeType;
        private LinkedList<String> cc;
        private LinkedList<String> bcc;

        /**
         * The builder constructor only receives the required attributes and this attributes
         * are the only ones that are defined “final” on the builder to ensure that their
         * values are set on the constructor.
         */

        public Builder(String from, LinkedList<String> to) {
            this.from = from;
            this.to = to;
        }

        /**
         * these below are ONLY METHODS to set the OPTIONAL values!
         * TODO: add validation for input data in below methods
         */
        public Builder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder mimeType(String mimeType) {
            this.mimeType = mimeType;
            return this;
        }

        public Builder mimeType(LinkedList<String> cc) {
            this.cc = cc;
            return this;
        }

        public Builder bcc(LinkedList<String> bcc) {
            this.bcc = bcc;
            return this;
        }

        public EmailMessage build() {
            return new EmailMessage(this);
        }
    }
}