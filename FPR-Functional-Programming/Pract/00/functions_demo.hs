module Main where

import Data.Char (chr, ord)

main :: IO ()
main = do
  print (letter 'a')
  print (letter 'B')
  print (letter '$')

func x y
  | x < y = x + y
  | x > y = x * y
  | otherwise = x - y

offset :: Int
offset = ord 'a' - ord 'A'

letter a =
  let charCode = ord a
   in let upperCodeA = ord 'A'
       in let lowerCodeA = ord 'a'
           in let lowerCodeZ = ord 'z'
               in if charCode >= lowerCodeA && charCode <= lowerCodeZ
                    then chr (charCode - (lowerCodeA - upperCodeA))
                    else a