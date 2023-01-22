package xu.zhixuan.wulne.Api.Minecraft.Launcher;

public interface IClassTransformer {

    byte[] transform(String name, String transformedName, byte[] basicClass);

}
