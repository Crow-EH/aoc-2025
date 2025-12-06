# aoc-2025

Welcome to the Advent of Code[^aoc] Kotlin project created by [crow-eh][github] using the [Advent of Code Kotlin Template][template] delivered by JetBrains.

In this repository, crow-eh is about to provide solutions for the puzzles using [Kotlin][kotlin] language.

If you're stuck with Kotlin-specific questions or anything related to this template, check out the following resources:

- [Kotlin docs][docs]
- [Kotlin Slack][slack]
- Template [issue tracker][issues]


[^aoc]:
    [Advent of Code][aoc] – An annual event of Christmas-oriented programming challenges started December 2015.
    Every year since then, beginning on the first day of December, a programming puzzle is published every day for twenty-five days.
    You can solve the puzzle and provide an answer using the language of your choice.

[aoc]: https://adventofcode.com
[docs]: https://kotlinlang.org/docs/home.html
[github]: https://github.com/crow-eh
[issues]: https://github.com/kotlin-hands-on/advent-of-code-kotlin-template/issues
[kotlin]: https://kotlinlang.org
[slack]: https://surveys.jetbrains.com/s3/kotlin-slack-sign-up
[template]: https://github.com/kotlin-hands-on/advent-of-code-kotlin-template


## Run with gradle / gradlew
```bash
gradle run -Pday=01
```
`day`: Any day that matches a `Day<day>.kt` file. Default: `01`


## Format Code
```bash
gradle spotlessApply
```
Or use a [ktfmt](https://github.com/facebook/ktfmt) plugin from your IDE.
