package ee.leemet.helmes.util

def filename = '/Users/sanderleemet/Desktop/helmes/src/main/groovy/sectors.txt'
def outputFilename = '/Users/sanderleemet/Desktop/helmes/src/main/groovy/output.txt'
File file = new File(filename)
File outputFile = new File(outputFilename)

outputFile.delete()

file.eachLine { line ->
	println(line)

	def id = line.split('"')[1]
	def name = line.split('[<>]')[2]
			.replace('&nbsp;', '')
			.replace('&amp;', '&')
			.trim()

	def sql = 'insert into sector (id, name) values (' + id + ', \'' + name + '\');'
	outputFile.append(sql + '\n')
}

//<option value="19">&nbsp;&nbsp;&nbsp;&nbsp;Construction materials</option>