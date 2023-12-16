# --- Day 4: Squid bingo game ---
# part 2

def apply_played_num(num, table):
    for i in range(len(table)):
        for j in range(len(table[i])):
            if table[i][j] == num:
                table[i][j] = "X"
                return table


def check_bingo(table):
    # check rows
    for i in range(len(table)):
        if table[i] == ["X", "X", "X", "X", "X"]:
            return True

    # check columns
    for i in range(len(table)):
        if table[0][i] == "X" and table[1][i] == "X" and table[2][i] == "X" \
                and table[3][i] == "X" and table[4][i] == "X":
            return True

    # check diagonals
    if table[0][0] == "X" and table[1][1] == "X" and table[2][2] == "X" and table[3][3] == "X" and table[4][4] == "X":
        return True

    if table[0][4] == "X" and table[1][3] == "X" and table[2][2] == "X" and table[3][1] == "X" and table[4][0] == "X":
        return True

    return False


def sum_all_nums(table):
    sum = 0
    for i in range(len(table)):
        for j in range(len(table[i])):
            if table[i][j] != "X":
                sum += int(table[i][j])
    return sum


def main():
    with open('input.txt', 'r') as f:
        lines = f.readlines()

    #print(lines[0][:-1:].split(", "))
    play_order = lines[0][:-1:].split(",")
    #print(play_order)
    bingo_tables = []

    # start at line index 1 take first 5 lines then skip 1 line and repeat
    # removing the \n at the end of each line
    for i in range(2, len(lines), 6):
        bingo_tables.append(lines[i:i+5])

    # remove \n at the end of each line
    for i in range(len(bingo_tables)):
        for j in range(len(bingo_tables[i])):
            bingo_tables[i][j] = bingo_tables[i][j][:-1:]
            # split each line into a list of numbers
            bingo_tables[i][j] = bingo_tables[i][j].split(" ")

            # remove empty strings
            bingo_tables[i][j] = list(filter(None, bingo_tables[i][j]))

    # for i in range(len(bingo_tables)):
    #     print(bingo_tables[i])


    # check if the item count is 5 in each list in each table

    # for i in range(len(bingo_tables)):
    #     for j in range(len(bingo_tables[i])):
    #         if len(bingo_tables[i][j]) != 5:
    #             print("Table: {} Row: {} has {} items".format(i+1, j+1, len(bingo_tables[i][j])))
    #             return


    #visualize the tables
    # for i in range(len(bingo_tables)):
    #     print("Table: {}".format(i+1))
    #     for j in range(len(bingo_tables[i])):
    #         print(bingo_tables[i][j])
    #     print()

    for round_num in play_order:
        #print("Round: {}".format(round_num))
        for table in bingo_tables:
            apply_played_num(round_num, table)
            if check_bingo(table):
                if len(bingo_tables) == 1:
                    val = sum_all_nums(table)
                    print(val)
                    print(int(round_num))
                    print(val * int(round_num))
                    return
                # remove table
                bingo_tables.remove(table)
                print("Bingo! Table removed")

        print(bingo_tables)
        print()


    #print(bingo_tables)

if __name__ == '__main__':
    main()

