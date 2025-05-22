Uninstall Appium via these steps:

## Run these commands to remove all npm and node_module
```
sudo rm -rf /usr/local/{lib/node{,/.npm,_modules},bin,share/man}/{npm*,node*,man1/node*}
sudo rm -rf /usr/local/bin/npm /usr/local/share/man/man1/node* /usr/local/lib/dtrace/node.d ~/.npm ~/.node-gyp
sudo rm -rf /opt/local/bin/node /opt/local/include/node /opt/local/lib/node_modules
sudo rm -rf /usr/local/bin/npm /usr/local/share/man/man1/node.1 /usr/local/lib/dtrace/node.d

```
## Delete all node and node_module folders
- Go to `/usr/local/lib` and delete `node` and `node_modules` folders
- Go to `/usr/local/include` and delete `node` and `node_modules` folders
- On MacOSX, If you installed with `brew install node`, then run `brew uninstall node` in your terminal
