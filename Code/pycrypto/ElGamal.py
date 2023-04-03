# idea: https://medium.com/asecuritysite-when-bob-met-alice/elgamal-and-elliptic-curve-cryptography-ecc-8b72c3c3555e

import hashlib

from zokrates_pycrypto.gadgets.pedersenHasher import PedersenHasher
import zokrates_pycrypto.field as field
from zokrates_pycrypto.babyjubjub import Point

'''
ElGamal encryption: pair of encryptions as field points
    P : point on the curve
    a : private key
    A : public key <- a.P
    M : message to be encrypted
    k : randomness
    cipher1 = k.P
    cipher2 = k.A + M
    sends (cipher1, cipher2) as encryptions
'''

'''
Elgamal Decryption:
    get S <- a.cipher1
    decrypt M <- cipher2 - S
'''

class ElGamal():
    def __init__(self):
        self.P = Point.from_hash(bytes([1,2]))  # seeded (helps with debugging)
        self.a = 2  # secretKey a = 2 (seeded)
        self.A = self.P.mult(self.a)

    def encryption(self, value, randomness):
        # M is Value and k is randomness
        cipher1 = self.P.mult(randomness)
        cipher2 = self.A.mult(randomness).__add__(value)
        return cipher1, cipher2

    def decryption(self, cipher1, cipher2):
        S = cipher1.mult(self.a)
        M = cipher2.__sub__(S)
        return M

# invoking the class
elgamal = ElGamal()

# get a valid point from M
value = Point.from_x(field.FQ(5))
print(value)

# calculate encryption
c0, c1 = elgamal.encryption(value, 5)  # the message must be a field element


# decrypt
value = elgamal.decryption(c0, c1)
print(value)
