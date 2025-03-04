#### Export below environment variables:
```
export REDIRECT_URI=""
export AUTH_USERNAME=""
export AUTH_ACCOUNT=""
export CLIENT_ID=""
export CLIENT_SECRET=""
```

#### Running Server in local: 
```mvn spring-boot:run```

#### Request:

```
curl --location --request POST 'http://localhost:8080/payment/consent/0015800000jfwxXAAQ'
```

#### Expected Response:
```
{"Data":{"ConsentId":"3c68f2bc-2844-4ba6-8e5c-ff7f2000875c","CreationDateTime":"2024-07-28T22:49:18.608Z","Status":"AwaitingAuthorisation","StatusUpdateDateTime":"2024-07-28T22:49:18.611Z","Initiation":{"InstructionIdentification":"instr-identification","EndToEndIdentification":"e2e-identification","InstructedAmount":{"Amount":"50.00","Currency":"GBP"},"CreditorAccount":{"SchemeName":"IBAN","Identification":"BE56456394728288","Name":"ACME DIY","SecondaryIdentification":"secondary-identif"},"RemittanceInformation":{"Unstructured":"Tools","Reference":"Tools"}}},"Risk":{"PaymentContextCode":"EcommerceGoods"},"Links":{"Self":"https://ob.sandbox.natwest.com/open-banking/v3.1/pisp/domestic-payment-consents/3c68f2bc-2844-4ba6-8e5c-ff7f2000875c"},"Meta":{"TotalPages":1}}
```

#### Pending: 
- API for Customer Approving the response is failing for some reason(but we have api for that). 
- For now, we can mark "customer consent Approved" from UI after some timer to make it presentable.

