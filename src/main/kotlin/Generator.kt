import java.io.File

fun main(args: Array<String>) {
    when (args.size) {
        2 -> M3uGenerator().findGroupsOfDiscs(File(args[0]), args[1])
        else -> throw IllegalArgumentException("Supply 2 arguments")
    }
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