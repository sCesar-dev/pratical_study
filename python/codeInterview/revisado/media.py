def media(array):
    if len(array) == 0: 
        return 0
        
    acum = 0
    
    # for value in array:
    #     acum += value
    # return acum / len(array)
    
    for i in range(0, len(array)):
        acum += array[i]
        
    return acum / len(array)

if __name__ == '__main__':
    
    lista = [1, 5, 3, 6, 4, 4, 5, 6, 7, 9]
    print(media(lista))