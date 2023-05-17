package org.ashcode.appdevwordjumbletask1rerough;

public class BtnModelInGrid {
    private boolean btnClickedState;
    private String btnTextLetter;
    private int btnBgColor;
    private int btnTextColor;
    private static int btnTextColorClicked = R.color.white;
    private static int btnTextColorUnclicked = R.color.black;

    private static int btnBgColorUnclicked = R.color.purple_200;

    private static int btnBgColorClicked = R.color.teal_200;

    public BtnModelInGrid(boolean btnClickedState, String btnTextLetter) {
        this.btnClickedState = btnClickedState;
        this.btnTextLetter = btnTextLetter;
        if (!btnClickedState) {
            this.btnBgColor = btnBgColorUnclicked;
            this.btnTextColor = btnTextColorUnclicked;
        } else {
            this.btnBgColor = btnBgColorClicked;
            this.btnTextColor = btnTextColorClicked;
        }

    }

    public boolean isBtnClickedState() {
        return btnClickedState;
    }

    public void setBtnClickedState(boolean btnClickedState) {
        this.btnClickedState = btnClickedState;
    }

    public String getBtnTextLetter() {
        return btnTextLetter;
    }

    public void setBtnTextLetter(String btnTextLetter) {
        this.btnTextLetter = btnTextLetter;
    }

    public int getBtnBgColor() {
        return btnBgColor;
    }

    public void setBtnBgColor(int btnBgColor) {
        this.btnBgColor = btnBgColor;
    }

    public int getBtnTextColor() {
        return btnTextColor;
    }

    public void setBtnTextColor(int btnTextColor) {
        this.btnTextColor = btnTextColor;
    }
}