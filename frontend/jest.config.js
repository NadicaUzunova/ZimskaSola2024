// jest.config.js
module.exports = {
  testMatch: ['**/*.test.js'],
  transformIgnorePatterns: ['node_modules/(?!axios)/'],
  moduleFileExtensions: ['js', 'jsx'],
  testEnvironment: 'jsdom',
};
