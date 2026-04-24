module Main where

main :: IO ()
main = do
  putStrLn "Enter the first number:"
  first <- getLine
  putStrLn "Enter the second number:"
  second <- getLine
  let a = read first :: Int
  let b = read second :: Int
  putStrLn ("The sum is " ++ show (a + b))
