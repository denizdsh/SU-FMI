-- !! Separate steps done in different styles to explore possible solutions !!
-- Potential optimization: directly evaluate "T | _ | _= T"; "F && _ && _ = F"

import Data.List (elemIndex, find)

-- import Debug.Trace (trace)

-- Helper functions
safeHead :: [a] -> Maybe a
safeHead [] = Nothing
safeHead (x : _) = Just x

safeTail :: [a] -> Maybe [a]
safeTail [] = Nothing
safeTail (_ : xs) = Just xs

removeItems :: [a] -> Int -> Int -> [a]
removeItems xs idx count = take idx xs ++ drop (idx + count) xs

insertItems :: String -> Int -> String -> String
insertItems xs idx ys = take idx xs ++ ys ++ drop idx xs

-- Constants
falseChar :: Char
falseChar = 'F'

trueChar :: Char
trueChar = 'T'

falsyValues :: [Char]
falsyValues = [falseChar, '0']

truthyValues :: [Char]
truthyValues = [trueChar]

andOperator :: Char
andOperator = '&'

orOperator :: Char
orOperator = '|'

notOperator :: Char
notOperator = '!'

operators :: [Char]
operators = [andOperator, orOperator, notOperator]

valueFallback :: Char
valueFallback = trueChar -- default value for characters outside of dictionary (for example: integer numbers, typos)

-- Task logic
baseReplaceValues :: [(Char, Char)]
baseReplaceValues =
  map (,falseChar) falsyValues
    ++ map (,trueChar) truthyValues
    ++ zip operators operators

replaceValues :: [(Char, Bool)] -> [(Char, Char)] --
replaceValues values =
  map (\(c, v) -> if v then (c, trueChar) else (c, falseChar)) values
    ++ baseReplaceValues

normalizeBoolExpr :: String -> [(Char, Bool)] -> String
normalizeBoolExpr e values =
  ( \c ->
      let pair = find (\(char, _) -> char == c) replace
       in case pair of
            Just (_, value) -> value
            Nothing -> valueFallback
  )
    `map` expr
  where
    expr = filter (/= ' ') e
    replace = replaceValues values

simplifyBoolExpr :: String -> String
-- remove redundant operators (& leftover T or F from multi-digit numbers)
simplifyBoolExpr expr = simplifyBoolExprHelper expr expr

simplifyBoolExprHelper :: String -> String -> String
-- could be done with idx
simplifyBoolExprHelper [] _ = []
simplifyBoolExprHelper expr [] = expr
simplifyBoolExprHelper expr exprCycle =
  case nextPair of
    Nothing -> expr
    Just (next, _) ->
      if curr == next
        then
          if curr == notOperator
            then simplifyBoolExpr (removeItems expr idx 2)
            else simplifyBoolExpr (removeItems expr idx 1)
        else simplifyBoolExprHelper expr $ tail exprCycle
  where
    indexed = zip exprCycle [0 ..]
    (curr, cycleIdx) : xs = indexed
    nextPair = safeHead xs
    idx = cycleIdx + (length expr - length exprCycle)

handleNotsBoolExpr :: String -> String
handleNotsBoolExpr = flip handleNotsBoolExprHelper 0

handleNotsBoolExprHelper :: String -> Int -> String
-- could be done with "find", "elemIndex"
handleNotsBoolExprHelper [] _ = []
handleNotsBoolExprHelper expr idx =
  case pair of
    Nothing -> expr
    Just (curr, next) ->
      if idx >= length expr -- function exhausted
        then expr
        else
          if curr == notOperator -- handle '!' and continue
            then
              handleNotsBoolExprHelper
                ( insertItems (removeItems expr idx 2) idx $
                    if next == trueChar then [falseChar] else [trueChar]
                )
                $ idx + 1
            else handleNotsBoolExprHelper expr $ idx + 1 -- continue cycling
  where
    exprCycle = drop idx expr
    pair = safeHead $ zip exprCycle $ tail exprCycle

handleLogicalOperator :: String -> Char -> (Char -> Char -> Char) -> String
handleLogicalOperator expr operator pred = case idx of
  Nothing -> expr
  Just idx ->
    handleLogicalOperator
      ( insertItems
          (removeItems expr (idx - 1) 3)
          (idx - 1)
          [pred (expr !! (idx - 1)) (expr !! (idx + 1))]
      )
      operator
      pred
  where
    idx = elemIndex operator expr

handleAndsBoolExpr :: String -> String
handleAndsBoolExpr expr =
  handleLogicalOperator
    expr
    andOperator
    (\left right -> if left == falseChar || right == falseChar then falseChar else trueChar)

handleOrsBoolExpr :: String -> String
handleOrsBoolExpr expr =
  handleLogicalOperator
    expr
    orOperator
    (\left right -> if left == trueChar || right == trueChar then trueChar else falseChar)

calcBoolExpr :: String -> [(Char, Bool)] -> Bool
calcBoolExpr expr values =
  case result of
    Nothing -> error "Invalid expression or values"
    Just result -> result == trueChar
  where
    result =
      safeHead
        . handleOrsBoolExpr
        . handleAndsBoolExpr
        . handleNotsBoolExpr
        . simplifyBoolExpr
        $ normalizeBoolExpr expr values

main :: IO ()
main = do
  let expr1 = "a && !!! b || c || T && !!T && 8821 && F || F"
  let expr2 = "b || 0 || 0 && !! a && b && ! c"
  let vals = [('a', True), ('b', False), ('c', True)]
  print $ calcBoolExpr expr1 vals -- True
  print $ calcBoolExpr expr2 vals -- False
  -- let norm = normalizeBoolExpr expr2 vals
  -- let simp = simplifyBoolExpr norm
  -- let nots = handleNotsBoolExpr simp
  -- let ands = handleAndsBoolExpr nots
  -- let ors = handleOrsBoolExpr ands
  -- print norm
  -- print simp
  -- print nots
  -- print ands
  -- print ors
