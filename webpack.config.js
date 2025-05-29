import fs from 'fs'
import MiniCssExtractPlugin from 'mini-css-extract-plugin'
import path from 'path'
import { fileURLToPath } from 'url'
import CopyPlugin from "copy-webpack-plugin";

const __dirname = path.dirname(fileURLToPath(import.meta.url))

const entries = {}
fs.readdirSync('./src/main/js/')
    .filter((fileName) => fileName.endsWith('.js'))
    .forEach((fileName) => {
        const entryName = fileName.replace(/\.js$/, '')
        entries[entryName] = `./src/main/js/${fileName}`
    })

export default {
    entry: entries,
    output: {
        filename: 'js/bundle-[name].js',
        path: path.resolve(__dirname, 'src/main/resources/static'),
        clean: true
    },
    mode: 'development',
    devtool: 'source-map',
    experiments: {
        topLevelAwait: true
    },
    module: {
        rules: [
            {
                test: /\.s?css$/i,
                use: [ MiniCssExtractPlugin.loader, 'css-loader', 'sass-loader' ]
            },
            {
                test: /\.(png|svg|jpe?g|gif)$/i,
                type: 'asset',
                generator: {
                    filename: 'images/[hash][ext][query]'
                }
            },
            {
                test: /\.(woff2?|eot|ttf|otf)$/i,
                type: 'asset',
                generator: {
                    filename: 'fonts/[hash][ext][query]'
                }
            }
        ]
    },
    plugins: [
        new MiniCssExtractPlugin({
            filename: 'css/bundle-[name].css'
        }),
        new CopyPlugin({
            patterns: [
                { from: 'src/main/images', to: 'images' }
            ]
        })
    ]
}
