import hashlib
import bitstring

from zokrates_pycrypto.gadgets.pedersenHasher import PedersenHasher
import zokrates_pycrypto.field as field
from zokrates_pycrypto.babyjubjub import Point
from elgamal import ElGamal      # E_R
from pedersen  import pedersenCommitment   # C_RA
import sys          # to take any system parameter
import random       # generate randomness
import math
import numpy as np


class HexToU32:
    def __init__(self):
        self.u32Array = []
        self.start = 2
    def conversion(self, digest):
        digest_bits = bitstring.BitArray(bytes=sys.ar)
        a= str(digest_bits)
        for j in range(8):
            temp = "0x"
            for i in range(self.start,self.start+8):
                temp = temp + a[i]
            self.start = self.start + 8
            u32Array.append(temp)
        
        return ', '.join(u32Array)

    
