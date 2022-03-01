public class ExtendedLetter extends Letter{
    private static final char c = 0;
    private String content;
    private int family;
    private boolean related;
    private int SINGLETON = -1;

    public ExtendedLetter(String s) { // set variables contructor
        super(c);

        content = s;
        related = false;
        family = SINGLETON;
    }

    public ExtendedLetter(String s, int fam) { // set varaibles constructor 2
        super(c);

        content = s;
        related = false;
        family = fam;
    }

    public boolean equals(Object other) {
        if (other instanceof ExtendedLetter) { // if instanace of ExtendedLetter   
            ExtendedLetter obj = (ExtendedLetter) other; // set obj to the other object
            if (this.family == obj.family) {  // if  the family of both objects is equal
                this.related = true; // true 
            }

            if (this.content == obj.content) {
                return true; // if content equaal to content of other return true
            }

            return false; // otherwise false
        }

        return false; // otherwise false
    }

    public String toString() {
        if (this.label == UNUSED && this.related == true) {
            return "." + this.content + ".";
        }

        return decorator() + this.content + decorator();
    }

    public static Letter[] fromStrings(String[] content, int[] codes) {
        Letter[] letters = new Letter[content.length];

        for (int i = 0; i <= letters.length - 1; i++) {
            if (codes == null) {
                letters[i] = new ExtendedLetter(content[i]);
            } else {
                letters[i] = new ExtendedLetter(content[i], codes[i]);
            }

        }

        return letters;
    }

}
