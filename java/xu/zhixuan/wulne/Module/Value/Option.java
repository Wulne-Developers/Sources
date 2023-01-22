package xu.zhixuan.wulne.Module.Value;

public class Option<V>
        extends Value<V> {
    public Option(String name, V enabled) {
        super(name);
        this.setValue(enabled);
    }
}

