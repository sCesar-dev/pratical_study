def bubble_sort(arr):
    for i in range(0, len(arr) - 1):
        for j in range(0, len(arr) - 1):
            if arr[j] > arr[j+1]:
                arr[j], arr[j+1] = arr[j+1], arr[j]

arr2 = [10, 5, 2, -2, 20, 10, 0, 7]
bubble_sort(arr2)
for i in arr2:
    print(i)