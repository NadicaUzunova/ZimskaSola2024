describe('template spec', () => {
  it('passes', () => {
    cy.visit('http://localhost:3000/products')
    cy.get('#addNewProductButton').click()
      .then(($el) => {
        cy.get('#addProductNameInput').type("test product")
        cy.get('#addProductMaxInput').type("15.0")
        cy.get('#addProductMinInput').type("5.0")
        cy.get('#addProductButton').click();
      })
  })
})