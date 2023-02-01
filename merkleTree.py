''' this file create a merkle tree
we want a tree with depth 20
using a sparse matrix
and with pedersen hash 
'''

# The implementation idea is borrowed from: 
# https://medium.com/coinmonks/implementing-merkle-tree-and-patricia-trie-b8badd6d9591



import hashlib
import bitstring
import numpy as np
import random


from zokrates_pycrypto.gadgets.pedersenHasher import PedersenHasher
import zokrates_pycrypto.field as field
from zokrates_pycrypto.babyjubjub import Point
from HexToU32 import HexToU32   # to return u32 hashes

import sys          # to take any system parameter
import random       # generate randomness
import math


# from https://stackoverflow.com/questions/27988235/bytearray-fromhex-doesnt-convert-when-no-letters-in-number
#def number_to_bytes(number):
#    byte_count = int(math.log(number, 256)) + 1
#    hex_string = '{:0{}x}'.format(number, byte_count * 2)
#    return bytes.fromhex(hex_string)

# define the node
class Node:
    def __init__(self):
        self.root = True
        self.tree = []  # initialize the array
        self.hasher = PedersenHasher(b"test")   # init the hasher with a random string
        self.neighbour_path = []
        self.typeCast = HexToU32()  # to store u32 hashes
    
    def createTree(self, valueArray):
        # bottom row with leaf values
        self.tree.insert(0,valueArray)   

        # now fill above row with Pedersen hashes
        tempHashes = [] # temp array to store hashes
        for i in valueArray:
            preimage = i.bytes     # get the preimage
            digest = self.hasher.hash_bytes(preimage)
            tempHashes.append(bitstring.BitArray(bytes=digest.compress()).bin)
        self.tree.insert(0,tempHashes)
        
        # now we take two items from the top most layer and hash them together
        # This process is repeated until the first item of the root has length equal to one, 
        # which would indicate that we have found the root hash.
        while(len(self.tree[0]) > 1):
            tempArray = []  # save the hashes in a temporary list

            for i in range(0,len(self.tree[0]),2):
                if i < len(self.tree[0])- 1 and i % 2 == 0:
                    # concatenate the combined hashes
                    tempSum = self.tree[0][i] + self.tree[0][i+1]
                    
                    # create the bytes array
                    tempPreImage = bitstring.BitArray(bin=tempSum).bytes
                    
                    # append the hash to the tree 
                    digest = self.hasher.hash_bytes(tempPreImage)
                    tempArray.append(bitstring.BitArray(bytes=digest.compress()).bin)
                else:
                    tempArray.append(self.tree[0][i])
            self.tree.insert(0,tempArray)
    
    # function to verify the existence of a leaf
    # gives the path
    def verifyTree(self, leafValue):
        position = self.tree[-1].index(leafValue)      # get the position of the leaf
        #print(position)
        tempHasher = self.hasher.hash_bytes(leafValue.bytes)   # get the hash
        tempRootHash = bitstring.BitArray(bytes=tempHasher.compress()).bin
        self.neighbour_path = []
        self.directionSelector = []


        # iterate through the list to find the path
        for i in range(len(self.tree) - 2, 0 , -1):    
            if (position % 2 == 0):
                self.neighbour_path.append(self.tree[i][position+1])
                self.directionSelector.insert(0,0)
                neighbour_hash = self.tree[i][position+1]
                position = math.floor(position/2)
                
                tempRootHash = tempRootHash + neighbour_hash # check the concatenation!!                tempRootHash = bitstring.BitArray(bin=tempRootHash).bytes
                tempRootHash = bitstring.BitArray(bin=tempRootHash).bytes
                tempHasher = self.hasher.hash_bytes(tempRootHash)   # get the hash
                tempRootHash = bitstring.BitArray(bytes=tempHasher.compress()).bin

            else:
                self.neighbour_path.append(self.tree[i][position-1])
                self.directionSelector.insert(0,1)
                neighbour_hash = self.tree[i][position-1]
                position =  math.floor((position-1)/2)


                tempRootHash = neighbour_hash + tempRootHash    # check the concatenation!!
                tempRootHash = bitstring.BitArray(bin=tempRootHash).bytes
                tempHasher = self.hasher.hash_bytes(tempRootHash)   # get the hash
                tempRootHash = bitstring.BitArray(bytes=tempHasher.compress()).bin
        
        # check if temp hash equals the root hash
        if tempRootHash == self.tree[0][0]:
            print("leaf is present")
        else:
            print("leaf is not present")

    def getPath(self):
        #np.save('u32path.npy', np.array(self.neighbour_path))
        print(self.neighbour_path)
        print(self.directionSelector)
        #print(self.typeCast.conversion(self.tree[0][0]))
        print(self.tree[0][0])
    
    def getTree(self):
        #np.save('pythiaBool2power19.npy', np.array(self.tree))
        print(self.tree)
    
    def loadTree(self):
        self.tree = np.load('pythiaBool2power17.npy', allow_pickle=True)


# function that returns 512 bit string of a number x
def get_bitStrings(x):
    return bitstring.BitArray(format(1, "#0514b"))




# driver commands
mt = Node()

# create the leaf list
l = []
for i in range(2**2):
    l.append(get_bitStrings(random. randint(0,22) ))

# create the tree
mt.createTree(l)

# save the tree
#mt.getTree()

# load the tree
#mt.loadTree()

# verify the path
#mt.verifyTree(get_bitStrings(1))

# get the path
#mt.getPath()
