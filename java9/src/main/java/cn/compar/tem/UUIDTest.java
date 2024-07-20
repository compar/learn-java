package cn.compar.tem;

import java.util.UUID;

import com.fasterxml.uuid.Generators;

/**
UUID 版本 1 (v1)由时间戳、单调计数器和 MAC 地址生成。
UUID 版本 2 (v2)保留用于没有已知详细信息的安全 ID 2。
UUID 版本 3 (v3)是根据您提供的一些数据的 MD5 哈希值生成的。RFC 建议将 DNS 和 URL 列为数据候选对象。
UUID 版本 4 (v4)完全由随机数据生成。这大概就是大多数人对 UUID 的看法和理解。
UUID 版本 5 (v5)是根据您提供的一些数据的 SHA1 哈希值生成的。与 v3 一样，RFC 建议将 DNS 或 URL 作为候选。
UUID 版本 6 (v6)由时间戳、单调计数器和 MAC 地址生成。这些数据与版本 1 相同，但顺序有所改变，因此排序时将按创建时间排序。
UUID 版本 7 (v7)是由时间戳和随机数据生成的。
UUID 版本 8（v8）是完全自定义的（除了所有版本都包含的必需版本/变体字段）。

您通常会在 v4 或 v7 之间进行选择。有时也会选择 v5 或 v8。

如果您只需要一个随机 ID，请使用 v4。 这是一个不错的默认选择。
如果您在需要排序的上下文中使用 ID，请使用 v7。例如，如果您使用 UUID 作为数据库键，请考虑使用 v7。
如果您想要在 UUID 中使用自己的数据，则使用 v5 或 v8，但通常情况下，您会知道是否需要它。

其它的呢？
根据 RFC，v7 是对 v1 和 v6 的改进，如果可能的话，应该优先使用它们。所以你通常不会想要 v1 或 v6。如果你确实想要其中之一，你可以使用 v6。
v2 是为未指定的安全事项保留的。如果您正在使用这些，您可能无法告诉我或任何其他人，并且您可能不会阅读这篇文章来了解有关它们的更多信息。
v3 已被 v5 取代，后者使用更强大的哈希。您可能知道是否需要它。
 */
public class UUIDTest {
    public static void main(String[] args) {

        
        UUID uuid1 = Generators.timeBasedGenerator().generate();
        UUID uuid3 = Generators.nameBasedGenerator().generate("DNS_OR_URL");
        UUID uuid4 = Generators.randomBasedGenerator().generate();
        UUID uuid5 = Generators.nameBasedGenerator().generate("DNS_OR_URL");
        UUID uuid6 = Generators.timeBasedReorderedGenerator().generate();  
        UUID uuid7 = Generators.timeBasedEpochGenerator().generate();  
        
        System.out.println(
            "\nuuid1: "+uuid1 +
            "\nuuid3: "+uuid3 +
            "\nuuid4: "+uuid4 +
            "\nuuid5: "+uuid5 +
            "\nuuid6: "+uuid6 +
            "\nuuid7: "+uuid7 
        );
        System.out.println("-------------------");
        for (int i = 0; i < 10; i++) {
            System.out.println(Generators.timeBasedEpochGenerator().generate());
            System.out.println(Generators.timeBasedEpochGenerator().generate());
            System.out.println(Generators.timeBasedEpochGenerator().generate());
            System.out.println(Generators.timeBasedEpochGenerator().generate());
            System.out.println(Generators.timeBasedEpochGenerator().generate());
            System.out.println();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        
    }
}
