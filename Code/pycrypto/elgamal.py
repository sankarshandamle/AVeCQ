import hashlib

from zokrates_pycrypto.gadgets.pedersenHasher import PedersenHasher
import zokrates_pycrypto.field as field
from zokrates_pycrypto.babyjubjub import Point

'''
ElGamal encryption: pair of encryptions as field points c0 and c1
            G : base point
            value : integer to be encrypted
            M1 <- G.value
            Y : public key created as G.secretKey
            r : randomness
            S1 <- Y.r
            c0 <- G.r
            c1 <- M1 + S1

'''

'''
Elgamal Decryption: 
        (1) get S1 with c0.secretKey
        (2) get M1 with c1 - S1
        (3) get "value" by brute force assertion
                (3.a) generate x \in [value]
                (3.b) M1 == G.x
'''

class ElGamal():
    def __init__(self):
        self.G = Point.from_hash(bytes([1,2]))  # seeded (helps with debugging)
        self.Y = self.G.mult(2)      # secretKey = 2 (seeded)
    
    def encryption(self, value, randomness):
        M1 = self.G.mult(value)
        S1 = self.Y.mult(randomness)
        # encryptions
        c0 = self.G.mult(randomness)
        c1 = M1.add(S1)
        return c0, c1
    
    def decryption(self, c0, c1, secretKey, range_of_value):    # range_of_value is an upper bound of value
        S1 = c0.mult(secretKey)
        S1_neg = S1.neg()
        M1 = c1.add(S1_neg)
        # now let's brute force!
        for i in range(range_of_value):
            if M1.__eq__(self.G.mult(i)):
                return i
        
        return 0

# invoking the class
elgamal = ElGamal()

# calculate encryption
c0, c1 = elgamal.encryption(5,5)
print(c0,c1)

# decrypt
value = elgamal.decryption(c0, c1, 2, 10)
print(value)
