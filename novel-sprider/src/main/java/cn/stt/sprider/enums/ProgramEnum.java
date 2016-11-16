//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.stt.sprider.enums;

public enum ProgramEnum implements BaseEnum {
    YIDU("yidu"),
    JIEQI("jieqi"),
    MINIMALIST("minimalist");

    private String name;

    private ProgramEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static ProgramEnum parseEnum(String vlaue) {
        ProgramEnum[] var4;
        int var3 = (var4 = values()).length;

        for(int var2 = 0; var2 < var3; ++var2) {
            ProgramEnum e = var4[var2];
            if(vlaue.equals(e.getName())) {
                return e;
            }
        }

        return YIDU;
    }
}
