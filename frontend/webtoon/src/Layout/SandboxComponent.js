import React from 'react';

import { Box, Grid, RoutedButton } from 'grommet';
import { Apps } from 'grommet-icons';

const SandboxComponent = props => (
  <Grid>
    <Box align='start'>
      <RoutedButton icon={<Apps />} path='/' hoverIndicator={true} />
    </Box>
    <Box {...props} />
  </Grid>
);

SandboxComponent.defaultProps = {
  align: 'center',
  pad: 'large',
};

export default SandboxComponent;
