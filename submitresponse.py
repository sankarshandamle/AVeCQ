# this file codes the SUBMITRESPONSE tuple 
# for a worker w_i
'''
The tuple consists of:
    ans: worker's answer
    pa: worker's public address
    q: worker's current quality
    rc_new: new randomness for the commitment 
    rc_old: old commitment randomness
    sk: secret key for the certificate
    pi: proof for ProveQual
'''

## seeded pa: 0x8869598831f1322b17eC31c135Acdcf84065604e

import hashlib

from zokrates_pycrypto.gadgets.pedersenHasher import PedersenHasher
import zokrates_pycrypto.field as field
from zokrates_pycrypto.babyjubjub import Point
from elgamal import ElGamal      # E_R
from pedersen  import pedersenCommitment   # C_RA
import sys          # take ans, pa, q, rc_new, rc_old, sk, and pi as input
import random       # generate randomness
import math

# from https://stackoverflow.com/questions/27988235/bytearray-fromhex-doesnt-convert-when-no-letters-in-number
def number_to_bytes(number):
    byte_count = int(math.log(number, 256)) + 1
    hex_string = '{:0{}x}'.format(number, byte_count * 2)
    return bytes.fromhex(hex_string)

# init
old_commitment = []

# inputs
ans = int(sys.argv[1])
pa = int(sys.argv[2],0)
q = int(sys.argv[3])
rc_old = int(sys.argv[4])
rc_new = int(sys.argv[5])
sk = int(sys.argv[6])
old_commitment.append(int(sys.argv[7]))
old_commitment.append(int(sys.argv[8]))
pi = sys.argv[9]

# generate E_R(ans||pa)
randomness =  random.getrandbits(128) # randomness for the encryption
m = int(str(ans) + str(pa))    # concatenation
elgamal = ElGamal()
E_R = elgamal.encryption(m,randomness)

# generate C_RA(q,rc_new)
pedersenCom = pedersenCommitment()
C_RA = pedersenCom.commitment(q,rc_new)

# generate E_R(r_tau)
r_tau = random.getrandbits(128) # randomness for the task
r_temp = random.getrandbits(128) # randomness for the encryption
E_R_rand = elgamal.encryption(r_tau,r_temp)

# # generate H(C_RA(),sk)
preimage = number_to_bytes(old_commitment[0]+old_commitment[1]+sk)

# create an instance with personalisation string
hasher = PedersenHasher(b"pythia") 
# hash payload
digest = hasher.hash_bytes(preimage)

# proof
# TBA
