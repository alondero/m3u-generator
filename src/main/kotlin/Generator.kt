import java.io.File

fun main(args: Array<String>) {
    require(args.size == 2) { "Supply 2 arguments" }
    M3uGenerator().findGroupsOfDiscs(File(args[0]), args[1])
}

class M3uGenerator {

    fun findGroupsOfDiscs(directory: File, discType: String = ".iso") {
        require(directory.isDirectory) { "Argument 1 - ${directory.absolutePath} is not a directory" }

        directory.listFiles{ file -> file.extension != discType }
            .groupBy{ it.name.substringBefore("(").trim() }
            .forEach{
                File(directory, "${it.key} (USA).m3u").writeText(it.value.joinToString(separator = "\n") { file -> file.name })
            }
    }

}