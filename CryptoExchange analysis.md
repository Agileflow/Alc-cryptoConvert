+ Overview link
	- API: https://min-api.cryptocompare.com/coins/btc/overview

+ API:
	- https://min-api.cryptocompare.com/data/pricemultifull?fsyms=BTC,ETH&tsyms=BTC,ETH,USD,EUR

+ Info:
	- Public APIs are accessible via GET, and the parameters for the request are included in the query string.

+ Authentication:
	- CryptoCompare uses session cookies to allow access to private data. You can obtain the cookie by logging in.

+ Sessions
	- A session key is only valid for 30 days and it has a sliding window period, so each time you use it, it will get extended by 30 days.

+ Responses
	- If successful, API requests will return an HTTP 200 OK code, as well as a Response.Type >= 100 any Response.Type less than 100 will mean there was an error.

	- Note that the responses to all requests, both public and private, are sent as the response body.

+ Crypto-Currencies
	- BTC - Bitcoin
	- ETH - Ethereum
	
+ World Currencies
	- NGN - Nigerian Naira
	- USD - US Dollar
	- EUR - Euro
	- JPY - Japanese Yen
	- GBP - Great Britain Pound
	- CHF - Swiss Franc
	- CAD - Canadian Dollar
	- AUD - Australian Dollar
	- HKD - Hong Kong Dollar
	- CNY - China Renminbi
	- ZAR - South African Rand
	- INR - Indian Rupee
	- NZD - New Zealand Dollar
	- RUB - Russian Ruble
	- TRY - Turkish Lira
	- AED - UAE Dirham
	- MYR - Malaysia Ringgit
	- CLP - Chilean Peso
	- ILS - Isreali New Shekel
	- SGD - Singapore Dollar
	- CZK - Czech Koruna

+ Collapsed
	- AED,AUD,CAD,CHF,CLP,CNY,CZK,EUR,GBP,HKD,ILS,INR,JPY,MYR,NGN,NZD,RUB,SGD,TRY,USD,ZAR