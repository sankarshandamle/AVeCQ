import hashlib

from zokrates_pycrypto.gadgets.pedersenHasher import PedersenHasher
import zokrates_pycrypto.field as field
from zokrates_pycrypto.babyjubjub import Point

'''
 Given the base point G and another point H, 
 a message m and a blinding r, 
 we just have to compute Comm(m,r)=m*G+r*H
'''

''' seeded public and private keys
G
x: 19117611221119290569771910015394303656830624589378450814079386143444437270213, y:13803021523418853632785048342469523090112227029170496262836766272854390110095 
H
x: 3094072593732653663001142309359180258220523071495687372947424291163094343404, y:7845294723712774932176556434331273686218977905161077318518335105292294334584
a
2
'''

class pedersenCommitment:
    def __init__(self):
        self.G = Point.generator()
        self.a = 2
        self.H = self.G.mult(self.a)

    def commitment(self,m,r):
        return (self.G.mult(m)+self.H.mult(r))


# invoke the commitment
com = pedersenCommitment()

# get the commitment
print(com.commitment(2,3))

# sanity check for homomorphism
#c1 = com.commitment(2,3)
#c2 = com.commitment(3,4)
#c = com.commitment(5,7)

#assert(c,(c1.add(c2)))
