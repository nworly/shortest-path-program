## Программа для поиска минимальных затрат перемещения существа из стартовой позиции в конечную

### Правила:
* существо может перемещаться ввех/вниз или влево/вправо
* стоимость перемещения по клеткам зависит от типа клетки и от расы существа в соответствии с таблицей:

|   race   | S (swamp) | W (water) | T (tree) | P (plain) |
|:--------:|:---------:|:---------:|:--------:| :-----: |
|  human   |     5     |     2     |    3     |  1 |
| swamper  |     2     |     2     |    5     |  2 |
| woodman  |     3     |     3     |    2     |  2 |  

*Информация о названиях существ и препятствий и стоимости перемещений считывается из файла **barrier-values-for-each-race.json**.*

### Устройство программы:
* класс `Solution` с единственным публичным, статическим методом `getResult`
* класс `Algorithm`, описывающий поиск кратчайшего пути на игровом поле из левого верхнего угла в правый нижний
  * статический класс `Cell` описывающий параметры клетки
  * статический метод `getShortestPath` для поиска кратчайшего пути (основан на алгоритме Дейкстры)

### Входные данные:
* строка длиной 16 символов, описывающая клетки игрового поля 4*4
* строка, содержащая расу существа

### Выходные данные:
* минимальные затраты существа на перемещение из стартовой позиции в конечную

### Пример:
* Входные данные: 
    * `"STWSWTPPTPTTPWPP"`
    * `"Human"`
* Выходные данные:
    * `10`

*Программа содержит Unit тесты.*

---

## This program is designed to find the minimum moving cost of a creature from its starting point to the final one in a matrix

### Rules:
* creature can move up/down or left/right
* moving cost through cells depends on the type of cell and on the creature race according to the table:

| race | S (swamp) | W (water) | T (tree) | P (plain) |
|:--------:|:---------:|:---------:|:--------:| :-----: |
| human | 5 | 2 | 3 | 1 |
| swamper | 2 | 2 | 5 | 2 |
| woodman | 3 | 3 | 2 | 2 |

*Information about the creature types, obstacles and the moving costs is read from the file **barrier-values-for-each-race.json**.*

### Program structure:
* class `Solution` with a single public static method `getResult`
* class `Algorithm`, describing the search for the shortest path on the field from the upper left corner to the lower right corner
  * static class `Cell` describing cell parameters
  * static method `getShortestPath` to find the shortest path (based on Dijkstra's algorithm)

### Input:
* a 16-character string describing the cells of the 4*4 playing field
* a string containing the creature's race

### Output:
* the minimum moving cost from the starting position to the final position

### Example:
* Input data:
    * `"STWSWTPPTPTTPWPP"`
    * `"Human"`
* Output:
    * `10`

*The program contains Unit tests.*
