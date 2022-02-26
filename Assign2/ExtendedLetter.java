public class ExtendedLetter extends Letter{
    private static final char c = 0;
    private String content;
    private int family;
    private boolean related;
    private int SINGLETON = -1;

    public ExtendedLetter(String s) {
        super(c);

        content = s;
        related = false;
        family = SINGLETON;
    }

    public ExtendedLetter(String s, int fam) {
        super(c);

        content = s;
        related = false;
        family = fam;
    }

    public boolean equals(Object other) {
        if (other instanceof ExtendedLetter) {
            ExtendedLetter obj = (ExtendedLetter) other;
            if (this.family == obj.family) {
                this.related = true;
            }

            if (this.content == obj.content) {
                return true;
            }

            return false;
        }

        return false;
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
