package example.com.vp.model;

public class Color {
    private String colorHex;
    static private int numberOfColor = 4;
    public Color(int number){
        switch (number){
            case 0:
                // greenery
                this.colorHex = "#31cc58";
                break;
            case 1:
                // red
                this.colorHex = "#ff5252";
                break;
            case 2:
                // tangerinn
                this.colorHex = "#ffbd2e";
                break;
            case 3:
                // blue
                this.colorHex = "#0088cc";
                break;
            default:
                // greenery
                this.colorHex = "#31cc58";
                break;
        }
    }
    public String getColorHex() {
        return colorHex;
    }

    static public int getNumberOfColor() {
        return numberOfColor;
    }
}
