replace :: String -> String
replace str = map (\c -> if c == ' ' then '\0' else c) str

removeItems :: [a] -> Int -> Int -> [a]
removeItems xs idx count = take idx xs ++ drop (idx + count) xs

insertItems :: String -> Int -> String -> String
insertItems xs idx ys = take idx xs ++ ys ++ drop idx xs


main :: IO ()
main = do
  print $ insertItems (removeItems "T&!F|T&T&T" 2 2) 2 "T" 