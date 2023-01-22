package xu.zhixuan.wulne.Module.Value;

public class Value<V> {
    public String name;
    public boolean drag;
    public float animX;
    public float animX1;
    public V value;

    public Value(String name){
        this.name = name;

    }

    public String getName(){
        return this.name;
    }

    public V getValue(){
        return this.value;
    }

    public void setValue(V val){
        this.value = val;
    }
}
