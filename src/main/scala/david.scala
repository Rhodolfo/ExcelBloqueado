object ExcelBloqueado {

  import com.arena.office.excel.{readExcelSheetMaxIndex,readExcelRaw,writeExcelRaw,writeExcelNewSheetRaw}
  import java.io.File

  def main(args: Array[String]) = {

    // Lectura de archivos
    val files = {
      val d = new File("dataIn")
      if (d.exists && d.isDirectory) {
        d.listFiles.filter(_.isFile).filterNot(_.getName endsWith "README").toList
          .map(x => (x.getAbsolutePath,x.getName))
      } else {
        throw new Error("Bad directory name: dataIn")
      }
    }

    // Funcion auxiliar
    def dogIt(file: String, name: String): Unit = {
      System.out.println("Leyendo: "+name)
      val sheets = 0 to readExcelSheetMaxIndex(file, "default")
      val iNum = sheets.head
      val nums = sheets.tail
      val dest = "dataOut/raw_"+name
      def initWrite(): Unit = {
        writeExcelRaw(dest, iNum.toString, readExcelRaw(file, iNum))
      }
      def restWrite(n: Int): Unit = {
        writeExcelNewSheetRaw(dest, n.toString, readExcelRaw(file, n))
      }
      System.out.println("Escribiendo: "+dest)
      initWrite()
      nums.foreach(restWrite)
    }

    // A trabajar
    files.foreach(x => dogIt(x._1,x._2))

  }

}
