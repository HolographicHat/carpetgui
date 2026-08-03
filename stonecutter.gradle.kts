plugins {
    id("dev.kikugie.stonecutter")
    id("net.fabricmc.fabric-loom") version "1.17-SNAPSHOT" apply false
}

stonecutter active "26.2"

// See https://stonecutter.kikugie.dev/wiki/config/params
stonecutter parameters {
    swaps["mod_version"] = "\"" + property("mod.version") + "\";"
    swaps["minecraft"] = "\"" + node.metadata.version + "\";"
    constants["release"] = property("mod.id") != "template"
    dependencies["fapi"] = node.project.property("deps.fabric_api") as String
    replacements {
        string(eval(current.version, "<1.21.11")) {
            replace("Identifier.fromNamespaceAndPath", "ResourceLocation.fromNamespaceAndPath")
        }

        string(eval(current.version, "<1.21.1")) {
            replace("ResourceLocation.fromNamespaceAndPath", "ResourceLocation.tryBuild")
        }
        string(eval(current.version, "<1.19.4")) {
            replace("ResourceLocation.tryBuild", "new ResourceLocation")
        }

        string(eval(current.version, "<1.21.11")) {
            replace("Identifier", "ResourceLocation")
        }

        string(eval(current.version, "<1.21.11")) {
            replace("component.UIComponents", "component.Components")
        }
        string(eval(current.version, "<1.21.11")) {
            replace("container.UIContainers", "container.Containers")
        }
        string(eval(current.version, "<1.21.11")) {
            replace("BaseUIComponent", "BaseComponent")
        }
        string(eval(current.version, "<1.21.11")) {
            replace("OwoUIGraphics", "OwoUIDrawContext")
        }
        string(eval(current.version, "<1.21.11")) {
            replace("import net.minecraft.util.Util", "import net.minecraft.Util")
        }
        string(eval(current.version, "<26.1")) {
            replace("PayloadTypeRegistry.serverboundPlay()", "PayloadTypeRegistry.playC2S()")
        }
        string(eval(current.version, "<26.1")) {
            replace("PayloadTypeRegistry.clientboundPlay()", "PayloadTypeRegistry.playS2C()")
        }
    }
}
