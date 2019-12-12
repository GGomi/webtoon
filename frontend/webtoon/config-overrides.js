const { override, addBabelPlugin, addDecoratorsLegacy, addWebpackAlias, useBabelRc } = require('customize-cra');
module.exports = override(
  addBabelPlugin('react-hot-loader/babel'),
  addDecoratorsLegacy(),
  addWebpackAlias({ 'react-dom': '@hot-loader/react-dom' }),
  useBabelRc(),
);
