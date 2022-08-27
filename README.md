# cli-parser

A command line utility that converts PRN and CSV files to JSON or HTML Table.

# How to build

The utility is built using Gradle Wrapper using following command.

```shell
./gradlew clean customFatJar
```

This builds a FAT jar that can be used to run the utility. Once the FAT jar is built can be run using following
commands.

- To convert PRN to HTML

```shell
cat ./Workbook2.prn| java -jar app/build/libs/all-in-one-jar.jar prn html
```

- To convert CSV to HTML

```shell
cat ./Workbook2.csv| java -jar app/build/libs/all-in-one-jar.jar csv html
```

- To convert CSV to JSON

```shell
cat ./Workbook2.csv| java -jar app/build/libs/all-in-one-jar.jar csv json
```

- To convert PRN to JSON

```shell
cat ./Workbook2.prn| java -jar app/build/libs/all-in-one-jar.jar prn json
```

### Github Actions
There are 4 workflows added to the repo.
- Build with Gradle
  - This builds the FAT jar
- Generate HTML from CSV and PRN
  - This on demand action to generate HTML
- Generate JSON from CSV and PRN
  - This on demand action to generate JSON
- Test JSON Converter
  - This Actions tests outputs of both CSV and PRN with JSON format
- Test HTML Converter
  - This Actions tests outputs of both CSV and PRN with HTML format