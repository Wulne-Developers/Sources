package xu.zhixuan.wulne.Command;

public class Command {
    private String name;
    private String[] names;
    public Command(String name, String... names){
        this.name = name;
        this.names = names;
    }

    public void run(String[] args){}

    public String getName(){
        return this.name;
    }

    public String[] getNames(){
        if (this.names.length == 0) {
            return new String[]{this.name};
        }

        String[] names = new String[this.names.length + 1];
        for (int i = 0; i < names.length; i++) {
            if(i == 0){
                names[0] = name;
                continue;
            }
            names[i] = this.names[i - 1];
        }
        return names;
    }
}
