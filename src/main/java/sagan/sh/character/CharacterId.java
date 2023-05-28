package sagan.sh.character;

public enum CharacterId {

    BLANKA("https://wiki.supercombo.gg/w/Street_Fighter_6/Blanka/Frame_data"),
    CAMMY("https://wiki.supercombo.gg/w/Street_Fighter_6/Cammy/Frame_data"),
    CHUNLI("https://wiki.supercombo.gg/w/Street_Fighter_6/Chun-Li/Frame_data"),
    DEEJAY("https://wiki.supercombo.gg/w/Street_Fighter_6/Dee_Jay/Frame_data"),
    DHALSIM("https://wiki.supercombo.gg/w/Street_Fighter_6/Dhalsim/Frame_data"),
    EHONDA("https://wiki.supercombo.gg/w/Street_Fighter_6/E.Honda/Frame_data"),
    GUILE("https://wiki.supercombo.gg/w/Street_Fighter_6/Guile/Frame_data"),
    JAIME("https://wiki.supercombo.gg/w/Street_Fighter_6/Jaime/Frame_data"),
    JP("https://wiki.supercombo.gg/w/Street_Fighter_6/JP/Frame_data"),
    JURI("https://wiki.supercombo.gg/w/Street_Fighter_6/Juri/Frame_data"),
    KEN("https://wiki.supercombo.gg/w/Street_Fighter_6/Ken/Frame_data"),
    KIMBERLY("https://wiki.supercombo.gg/w/Street_Fighter_6/Kimberly/Frame_data"),
    LILY("https://wiki.supercombo.gg/w/Street_Fighter_6/Lily/Frame_data"),
    LUKE("https://wiki.supercombo.gg/w/Street_Fighter_6/Luke/Frame_data"),
    MANON("https://wiki.supercombo.gg/w/Street_Fighter_6/Manon/Frame_data"),
    MARISA("https://wiki.supercombo.gg/w/Street_Fighter_6/Marisa/Frame_data"),
    RYU("https://wiki.supercombo.gg/w/Street_Fighter_6/Ryu/Frame_data"),
    ZANGIEF("https://wiki.supercombo.gg/w/Street_Fighter_6/Zangief/Frame_data"),
    ;

    private final String frameDataURL;

    CharacterId(String frameDataURL) {
        this.frameDataURL = frameDataURL;
    }

    public String getFrameDataURL() {
        return frameDataURL;
    }
}
