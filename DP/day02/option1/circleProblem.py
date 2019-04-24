import numpy as np
import copy
def testmyVersion(array):
    bruteTot= getBiggestForce(array)
    myTot , path2 = getBiggestMaybe2(array)
    myTot2 , path3 = getBiggestMaybe(array)
    myTot3 , path4 = getBiggestCentered(array)
    myTot = max(myTot,myTot2,myTot3)
    # print("Expected:", bruteTot)
    # print("Got     :", myTot)
    # print("Got also:", myTot2)
    # if bruteTot != myTot:
        # print("NOOOOOOOOO")
    #     retrace(path2,array)
    return myTot/bruteTot
def getBiggestCentered(array):
    _, left, maxdex,right = testRangeCircular(array)
    values = 0
    path = []
    if abs(right-(maxdex+1)%len(array)) > 1:
        if right < maxdex:
            between = np.concatenate((array[maxdex+1:], array[0:right]))
            left -= right
            maxdex -= right
            right = 0
            array = array[right:maxdex+1]
        else:
            between = array[maxdex+1:right]
            array = np.concatenate((array[0:maxdex+1], array[right:]))
            if left > maxdex:
                left -= right - maxdex - 1
            right = maxdex +1

        val, p = solveRange(between)
        # if pathDex > len()
        [path.append(pathDex+ maxdex+1) for pathDex in p]
        values += val

    if abs(left-(maxdex-1)%len(array)) > 1:
        if left > maxdex:
            between = np.concatenate((array[left+1:],array[0:maxdex]))[::-1]
            left -= maxdex
            right -= maxdex
            maxdex = 0
            array = array[maxdex:left+1]
        else:
            between = array[left+1:maxdex]
            array = np.concatenate((array[0:left+1], array[maxdex:]))
            maxdex = (left+1)%len(array)
        val, p = solveRange(between)
        # if pathDex > len()
        [path.append(pathDex+ left+1) for pathDex in p]
        values += val
    values += array[(maxdex-1)%len(array)]*array[maxdex]*array[(maxdex+1)%len(array)]
    path.append(maxdex)
    array = cutArray(maxdex,array)

    if len(array):
        val, p = getBiggestCentered(array)
        values+= val
        path.append(p)

    return values, path


def testRangeCircular(array):
    ideals = []
    rights = [None]*len(array)
    lefts = [None]*len(array)
    for i,v in enumerate(array):
        right=(i+1)%len(array)
        left=(i-1)%len(array)
        leftmax = array[left]
        rightmax =array[right]
        lefts[i] = left
        rights[i] = right
        for j in range((len(array)-3)//6):
            right = (right + 3)%len(array)
            left = (left - 3)%len(array)
            if leftmax < array[left]:
                leftmax = array[left]
                lefts[i] = left
            if rightmax < array[right]:
                rightmax = array[right]
                rights[i] = right
        ideals.append(leftmax*rightmax*v)
    imax = np.argmax(ideals)
    return ideals[imax], lefts[imax],imax,rights[imax]

def testRange(array):
    if len(array)<3:
        return 0
    ideals = []
    for i,v in enumerate(array):
        if 0<i<len(array)-1:
            left = array[i-getIdeal(array[0:i],-1)-1] if i > 3 else array[i-1]
            right= array[i+getIdeal(array[i+1:],1)+1] if i < len(array)-4 else array[i+1]
            ideals.append(left*right*v)
    return np.argmax(ideals)+1

def solveRange(array):
    # if len(array)<3:
    #     print("Shouldnt happen")
    #     print(array[3])
    if len(array) ==3:
        return array[0]*array[1]*array[2],[1]

    maxdex= testRange(array)
    left = maxdex - getIdeal(array[0:maxdex],-1)-1 if maxdex > 3 else maxdex-1
    right= maxdex + getIdeal(array[maxdex+1:],1)+1 if maxdex < len(array)-3 else maxdex+1
    values = 0
    path = []
    if right > maxdex+1:
        val, p = solveRange(array[maxdex+1:right])
        array = np.concatenate((array[0:maxdex+1], array[right:]))
        [path.append(pathDex+ maxdex+1) for pathDex in p]
        values += val

    if left < maxdex-1:
        val, p = solveRange(array[left+1:maxdex])
        array = np.concatenate((array[0:left+1], array[maxdex:]))
        [path.append(pathDex+ left+1) for pathDex in p]
        values += val
        maxdex = left+1

    values += array[maxdex-1]*array[maxdex]*array[maxdex+1]
    path.append(maxdex)
    # print(left)
    # print(right)
    array = np.concatenate((array[0:left],array[right+1:]))

    if len(array):
        val, p = solveRange(array)
        values+= val
        path.append(p)

    return values, path

def getIdeal(array,direction):
    if direction >0:
        return np.argmax(array[0::3]) *3
    else:
        return np.argmax(array[-1::-3]) *3

    # arr2 = copy.deepcopy(array)
    # arr3 = copy.deepcopy(array)
    # arr3 = np.insert(arr3,[0,0],[0,0])
    # arr2 = np.insert(arr2,[0,len(arr2)],[0,0])
    # array = np.insert(array,[len(array),len(array)],[0,0])
    # ones = array * arr2 * arr3
    # maxval = np.argmax(ones)

def getBiggestMaybe2(array):
    if not len(array):
        return (0,[])
    listValues = np.array([multint(index,array) for index,value in enumerate(array)])

    cuml = 1
    for i,v in enumerate(listValues):
        cuml*=listValues[i]
    likelihood = [v**2/cuml for i,v in enumerate(listValues)]
    least = np.argmin(likelihood)
    tot, path = getBiggestMaybe2(cutArray(least,array))
    return tot + listValues[least], [least] + path

def getBiggestMaybe(array):
    if not len(array):
        return (0,[])
    listValues = np.array([multint(index,array) for index,value in enumerate(array)])
    bins = [[],[],[]]
    binsums = []
    for i,v in enumerate(listValues):
        bins[i%3].append(v)
    binsums = [sum(bins[i]) for i,v in enumerate(bins)]
    likelihood = [v/binsums[i%3] for i,v in enumerate(listValues)]
    least = np.argmin(likelihood)
    tot, path = getBiggestMaybe(cutArray(least,array))
    return tot + listValues[least], [least] + path

def getBiggestForce(array):
    if not len(array):
        return 0
    listValues = multArray(array)
    # print(listValues)
    maxValues= [getBiggestForce(cutArray(i,array)) for i,value in enumerate(array)]
    maxValues = np.array(maxValues)+listValues
    maxdex = np.argmax(maxValues)
    # oldpath = paths[maxdex]
    # if not len(oldpath):
    #     path = [maxdex]
    #     # print(path)
    # else:
    #     path =  [maxdex] + oldpath
    # print(path)
    return maxValues[maxdex]

def retrace(path,array):
    for i in path:
        print("Index= ",i)
        print(array)
        print("["+"   "*i + "^")
        array = cutArray(i,array)

def multArray(array):
    # print(array)
    arr3 = np.roll(array,1)
    arr2 = np.roll(array,-1)
    return arr3 * arr2 * array

def multint(index, array):
    #return multiplied number and new array
    # array2 = copy.deppcopy(array)
    pre = (index -1) %len(array)
    post = (index +1) %len(array)
    return array[pre] * array[post] * array[index]

def cutArray(index, array):
    array2 = copy.deepcopy(array)
    pre = (index -1) %len(array)
    post = (index +1) %len(array)
    if pre < post:
        return np.concatenate((array[0:pre],array[post+1:len(array)]))
    else:
        return np.array(array[post+1:pre])

if "__main__" == __name__:
        # print(getBiggestForce([1,2,3,4,5,6]))
        # print(getBiggestForce([2,4,6,8,9,6,4,2,4,24,67,2,3,34,100]))
        # print(getBiggestForce([2,4,6,8,9,4,400,2,4,24,67,2,3,34,100]))
        # for i in range(5):
        #     for j in range(3):
        #         array = np.random.randint(0,500,6 +i*3)
        #         answer = getBiggestForce(array)
        #         print(array)
        #         print(answer)


        array = np.random.randint(0,100,60)
        print(','.join(str(e) for e in array))
        array = np.random.randint(0,50,120)
        print(','.join(str(e) for e in array))
        array = np.random.randint(-50,50,30)
        print(','.join(str(e) for e in array))
        tot = 1
        for i in range(3,120,3):
            tot *= i
        ips = 1000000 * 133740
        spy = 60* 60 * 24* 365
        ageofuniverse = 14000000000
        numcomputers = 3000000000
        numplanets = 1000000000000000000000
        print(((tot/ips)/spy)/ageofuniverse/numcomputers/numplanets)
        # array = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15]
        # answer = getBiggestForce(array)
        # print(array)
        # print(answer)
        # array = [2,4,6,8,9,4,400,2,4,24,67,2,3,34,100]
        # answer = getBiggestForce(array)
        # print(array)
        # print(answer)
        # array = [2,4,6,8,9,6,4,2,4,24,67,2,3,34,100]
        # answer = getBiggestForce(array)
        # print(array)
        # print(answer)
        # # testmyVersion([1,9,3,4,5,8,6,2,13])
        #
        # testmyVersion([1,100,1,6,20,100,100,10,11])
        # array =[10,7,13,7,10,10,7,13,7]
        #
        # array= [10,1,1,1,100,1,1,1,10,1,1,10]
        # array = np.random.randint(1,100,15)
        # print(array)
        # print(testRangeCircular(array))
        # print(getIdeal(array,1))
        # values, path = solveRange(np.array(array))
        # print("Values",values)
        # retrace(path,[10,1,1,1,10,1,1,1,10])
        # bruteTot , path1 =getBiggestForce(array)
        # retrace(path1, array)
        # for i in range(3,19,3):
        #     print(i)
        #     howgood = 0
        #     num= 0
        #     for j in range(100):
        #         array = list(np.random.randint(1,1000,i))
        #         howgood += testmyVersion(array)
        #         num += 1
        #     print("Average Rate:",howgood/num)
